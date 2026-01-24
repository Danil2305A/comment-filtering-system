package org.stepik;

public class Main {
    static void main() {
        String[] spamKeywords = new String[] {
                "без процентов",
                "кредит",
                "вклад",
                "инвестиц",
                "бонус",
                "казино",
                "ставк"
        };

        String[] negativeKeywords = new String[] {
                "туп",
                "дебил",
                "ублюд",
                "мразь",
                "заткнись",
                "параша",
                "чмо"
        };

        int commentMaxLength = 70;

        TextAnalyzer[] analyzers = new TextAnalyzer[] {
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer(negativeKeywords),
                new TooLongTextAnalyzer(commentMaxLength)
        };

        String[] comments = new String[] {
                "кредит на 2 года без процентов и переплат",
                "предлагаем выгодный вклад с большой ежемесячной ставкой",
                "параша, а не фильм. Не рекомендую",
                "ну ты и чмо",
                "ты что совсем дебил такое говорить?!",
                "сериал на любителя, в целом можно смотреть",
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"
        };

        AnalyzerService analyzerService = new AnalyzerService();

        for (String comment : comments) {
            System.out.println(comment);
            Label label = analyzerService.checkLabels(analyzers, comment);
            System.out.println(label.name());
        }
    }
}
