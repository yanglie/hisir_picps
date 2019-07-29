package com.hisir.picps;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Integer a = 1;
		Integer b = 2;
		list.add(a);
		list.add(b);
		System.out.println(a.equals(b));
		b = 1;
		System.out.println(a.equals(b));
		for (Integer t: list) {
			t=3;
		}
		list.remove(1);
		
		list.remove(10);
		System.out.println(list);
	}

}
