package testo;

public class TranslateKwan {
    public static void main(String[] args) {
        System.out.println(translate("sanaz"));
    }

    public static String translate(String text) {
        StringBuilder builder = new StringBuilder();
        String vowels = "aeiou";
        boolean prevVowel = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (vowels.indexOf(c) != -1) {
                if (!prevVowel) {
                    builder.append("av");
                }
                prevVowel = true;
            } else {
                prevVowel = false;
            }
            builder.append(c);
        }
        return builder.toString();
    }
}
