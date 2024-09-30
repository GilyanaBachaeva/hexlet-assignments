package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    private String str;

    public ReversedSequence(String str) {
        this.str = str;
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int index) {
        return str.charAt(str.length() - 1 - index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        StringBuilder sb = new StringBuilder(str.substring(start, end));
        return sb;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
}
// END
