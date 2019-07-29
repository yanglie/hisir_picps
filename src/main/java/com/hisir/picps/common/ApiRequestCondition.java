package com.hisir.picps.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.condition.RequestCondition;


public  class ApiRequestCondition implements RequestCondition<ApiRequestCondition> {

	
	private double minVersion;
	private double maxVersion;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	ApiRequestCondition(double min,double max) {
		minVersion = min;
		maxVersion = max;
	}
	
	@Override
	public ApiRequestCondition combine(ApiRequestCondition other) {
		logger.info("ApiRequestCondition combining ");
		return null;
	}

	@Override
	public ApiRequestCondition getMatchingCondition(HttpServletRequest request) {
		String versionString = request.getHeader("ApiVersion");
		if (versionString != null) {
			try {
				double ver = Double.valueOf(versionString);
				
				// if the ApiVersion is between the annotation properties, matched
				if (this.minVersion <= ver && ver <= this.maxVersion) {
					logger.info("version matched for {}/{},with min {},max {}",request.getServletPath(),ver,this.minVersion,this.maxVersion);
					return this;
				}
			} catch (NumberFormatException e) {
				return null;
			}
		}
		
		return null;
	}

	@Override
	public int compareTo(ApiRequestCondition other, HttpServletRequest request) {

		logger.warn("Request {} have overlapped version segment defined",request.getServletPath());
		/* it comes to here because version segment is overlapped, for example:
		 * two handler methods annotated with apiversion each
		 * ApiVersion(1.0,3.0)
		 * ApiVersion(2.0,4.0)
		 * 
		 * And client request version 2.5, both of them will match it and the spring mvc will compare
		 * them which should not happen.(don't define overlapped version segments)
		 * 
		 * But we will try to use the latest version as possible
		 */
		double myMinVersion = this.minVersion;
		double myMaxVersion = this.maxVersion;
		double otherMinVersion = other.minVersion;
		double otherMaxVersion = other.maxVersion;
		
		if (myMaxVersion > otherMaxVersion) {
			return -1;
		} else if (myMaxVersion < otherMaxVersion) {
			return 1;
		} else {
			// both max version is the same, compare the min version
			if (myMinVersion >= otherMinVersion) {
				return -1;
			} else {
				return 1;
			}
		}
	}



}
