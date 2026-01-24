package org.stepik;

public class Main {
    public static void main(String[] args) {
        String[] spamKeywords = new String[] {
                "Без процентов",
                "Кредит",
                "Вклад",
                "Инвестиц",
                "Бонус",
                "Казино",
                "Ставк"
        };

        String[] negativeKeywords = new String[] {
                "Туп",
                "Дебил",
                "Ублюд",
                "Мразь",
                "Заткнись",
                "Параша",
                "Чмо"
        };

        int commentMaxLength = 70;

        TextAnalyzer[] analyzers = new TextAnalyzer[] {
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer(negativeKeywords),
                new TooLongTextAnalyzer(commentMaxLength)
        };

        String[] comments = new String[] {
                "Кредит на 2 года без процентов и переплат",
                "Предлагаем выгодный вклад с большой ежемесячной ставкой",
                "Параша, а не фильм. Не рекомендую",
                "Ну ты и чмо",
                "Ты что совсем дебил такое говорить?!",
                "Сериал на любителя, в целом можно смотреть",
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
