package apps;

import java.util.List;

import tables.HashArrayTable;
import tables.SearchTable;
import tables.Table;

/**
 * Sandbox for execution of arbitrary code
 * for testing or grading purposes.
 * <p>
 * Modify the code for your use case.
 */
@Deprecated
public class Sandbox {
	public static void main(String[] args) {
		HashArrayTable table = new HashArrayTable(
			"sandbox_1",
			List.of("letter", "order", "vowel"),
			List.of("string", "integer", "boolean"),
			0
		);
//		table.put(List.of("alpha", 1, true));
//		table.put(List.of("beta", 2, false));
//		table.put(List.of("gamma", 3, false));
//		table.put(List.of("delta", 4, false));
//		table.put(List.of("tau", 19, false));
//		table.put(List.of("pi", 16, false));
//		table.put(List.of("omega", 24, true));
		table.remove("c7");
		table.put(List.of("a25", 0, true));
		table.put(List.of("f2", 0, false));
		table.put(List.of("b3", 30358, false));
		table.put(List.of("a", 5971, true));
		table.put(List.of("f8", 0, true));
		table.put(List.of("c", 1204795, true));
		table.put(List.of("aa6f2", 26905, false));
		table.remove("d");
		table.put(List.of("b11", 243, true));
		table.put(List.of("bb", 20, false));
		table.put(List.of("dab", 65057, false));
		table.put(List.of("a3", 0, true));
		table.put(List.of("a1", 5, false));
		table.put(List.of("eb1", 0, false));
		table.put(List.of("f1", 19, true));
		table.put(List.of("e", 0, true));
		table.put(List.of("cf", 2, false));
		table.remove("f");
		table.put(List.of("f2", 0, false));
		table.remove("b2");
		table.put(List.of("b7b", 11958, true));
		table.get("cb");
		table.put(List.of("c11", 0, true));
		table.remove("e6");
		table.put(List.of("a2", 91, true));
		table.put(List.of("b95", 0, false));
		table.put(List.of("c", 0, true));
		table.put(List.of("a2b0", 1387, false));
		table.put(List.of("a", 1, false));
		table.put(List.of("a18", 0, false));
		table.put(List.of("afa", 0, false));
		table.put(List.of("e", 0, true));
		table.remove("cb");
		table.put(List.of("b", 10, true));
		table.remove("fd3");
		table.put(List.of("b41", 0, true));
		table.put(List.of("bea", 3, false));
		table.put(List.of("d56", 0, true));
		table.put(List.of("b525", 8876, true));
		table.put(List.of("ae", 0, false));
		table.put(List.of("c8", 5, false));
		table.remove("d0a7");
		table.put(List.of("a", 0, false));
		table.put(List.of("ed", 3636, false));
		table.put(List.of("d", 0, false));//
		table.put(List.of("ef0", 0, true));
		table.put(List.of("bf", 593, true));
		table.put(List.of("c", 0, true));
		table.put(List.of("e1", 0, true));
		table.remove("a94");
		table.put(List.of("f", 0, true));
		table.remove("d");
		table.put(List.of("c7", 0, true));
		table.remove("d");
		table.put(List.of("b1", 0, false));
		table.remove("fee0");
		table.put(List.of("f9", 0, false));
		table.get("c");
		table.put(List.of("fc", 18, true));
		table.get("b");
		table.remove("abed3");
		table.put(List.of("b53a", 198, false));
		table.put(List.of("a41", 66, false));
		table.remove("d");
		table.put(List.of("f427", 0, true));
		table.put(List.of("b", 2514, true));
		table.put(List.of("a", 0, false));
		table.put(List.of("e64", 5660, false));
		table.put(List.of("df8", 35, false));
		table.remove("d4b");
		table.remove("e1b");
		table.put(List.of("c", 11434, true));
		table.put(List.of("e", 31, true));
		table.put(List.of("f", 38, false));
		table.put(List.of("bf0", 20674, true));
		table.put(List.of("df", 0, true));
		table.put(List.of("deb", 0, true));
		table.put(List.of("af", 0, false));
		table.remove("d10");
		table.put(List.of("a", 266, false));
		table.put(List.of("f219", 0, true));
		table.put(List.of("ca7e", 0, true));
		table.remove("a");
		table.put(List.of("b8", 0, false));
		table.remove("a");
		table.put(List.of("e6", 0, false));
		table.remove("df");
		table.put(List.of("b", 1191, false));
		table.remove("b55b");
		table.put(List.of("b4", 13132, false));
		table.put(List.of("d4", 803, true));
		table.put(List.of("d64a", 0, false));
		table.put(List.of("c", 52116, false));
		table.put(List.of("d", 37, true));//
		System.out.println(table.get("d"));
		table.put(List.of("a", 32, true));
		System.out.println(table.get("d"));
		table.put(List.of("bff", 21, false));
		System.out.println(table.get("d"));
		table.put(List.of("d52", 29, false));
		System.out.println(table.get("d"));
		table.put(List.of("a8fd", 10, false));
		System.out.println(table.get("d"));
		table.put(List.of("b", 700, false));
		System.out.println(table.get("d"));
		table.remove("c");
		table.put(List.of("e7", 0, false));
		System.out.println(table.get("d"));
		table.put(List.of("a2f5", 0, true));
		System.out.println(table.get("d"));
		table.get("f7");
		table.put(List.of("b", 0, false));
		System.out.println(table.get("d"));
		table.remove("ee");
		table.put(List.of("e5", 0, false));
		System.out.println(table.get("d"));
		table.put(List.of("a5", 42, false));
		System.out.println(table.get("d"));
		table.get("e");
		table.put(List.of("e", 7, true));
		System.out.println(table.get("d"));
		table.put(List.of("cf", 0, true));
		System.out.println(table.get("d"));
		table.put(List.of("b2", 0, true));
		System.out.println(table.get("d"));
		table.remove("c1");
		System.out.println(table.get("d"));

		System.out.println(table);
	}
}
