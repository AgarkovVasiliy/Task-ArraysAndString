package arraysAndString;

/*Метод осуществляющий сжатие строки на основании счетчика повторяющих символов.
 * Врямя выполнение compress - это O(p + k^2), где p - размер исходной строки,
 * а k - колличество последовательносте символов. 
 * compressAlternative & compress2 - O(N) - времени и O(N) - пространства. */

public class Compress {

	public String compress(String s) {
		String mystr = "";
		int count = 1;
		char last = s.charAt(0);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == last) {
				count++;
			} else {
				mystr += last + "" + count;
				last = s.charAt(i);
				count = 1;
			}
		}
		return mystr + last + count;
	}

	public String compress2(String s) {
		int size = countCompressor(s);
		if (size >= s.length()) {
			return s;
		}
		StringBuffer mystr = new StringBuffer();
		int count = 1;
		char last = s.charAt(0);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == last) {
				count++;
			} else {
				mystr.append(last);
				mystr.append(count);
				last = s.charAt(i);
				count = 1;
			}
		}
		mystr.append(last);
		mystr.append(count);
		return mystr.toString();
	}

	public String compressAlternative(String s) {
		int size = countCompressor(s);
		if (size >= s.length()) {
			return s;
		}
		char[] mystr = new char[size];
		int count = 1;
		int index = 0;
		char last = s.charAt(0);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == last) {
				count++;
			} else {
				index = setChar(s, mystr, last, index, count);
				last = s.charAt(i);
				count = 1;
			}
		}
		index = setChar(s, mystr, last, index, count);
		return String.valueOf(mystr);
	}

	int setChar(String s, char[] arr, char c, int index, int count) {
		arr[index] = c;
		index++;
		char cnt[] = String.valueOf(count).toCharArray();
		for (char d : cnt) {
			arr[index] = d;
			index++;
		}
		return index;
	}

	int countCompressor(String s) {
		int size = 0;
		int count = 1;
		char last = s.charAt(0);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == last) {
				count++;
			} else {
				last = s.charAt(i);
				size += 1 + String.valueOf(count).length();
				count = 0;
			}
		}
		size += 1 + String.valueOf(count).length();
		return size;
	}

	public static void main(String[] args) {
		System.out.println(new Compress().compressAlternative("abccccccccecccectt"));
		System.out.println(new Compress().compress2("abcccectt"));
		System.out.println(new Compress().compress("abctt"));
	}

}
