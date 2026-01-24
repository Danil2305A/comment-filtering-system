package org.stepik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpamAnalyzerTest {
    private SpamAnalyzer spamAnalyzer;
    private String[] spamKeywords;
    private String[] spamComments;
    private String[] notSpamComments;

    @BeforeEach
    public void setUp() {
        spamKeywords = new String[] {
                "Без процентов",
                "Кредит",
                "Вклад",
                "Инвестиц",
                "Бонус",
                "Казино",
                "Ставк"
        };

        spamComments = new String[] {
                "Кредит на 2 года без процентов и переплат",
                "Предлагаем выгодный вклад с большой ежемесячной ставкой"
        };

        notSpamComments = new String[] {
                "Параша, а не фильм. Не рекомендую",
                "Ну ты и чмо",
                "Ты что совсем дебил такое говорить?!",
                "Сериал на любителя, в целом можно смотреть",
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"
        };

        spamAnalyzer = new SpamAnalyzer(spamKeywords);
    }

    @Test
    @DisplayName("Тестирование проверки комментариев на спам")
    public void testCommentsIsSpam() {
        for (String spamComment : spamComments) {
            Assertions.assertEquals(Label.SPAM, spamAnalyzer.processText(spamComment));
        }
    }

    @Test
    @DisplayName("Тестирование проверки комментариев не на спам")
    public void testCommentIsNotSpam() {
        for (String notSpamComment : notSpamComments) {
            Assertions.assertNotEquals(Label.SPAM, spamAnalyzer.processText(notSpamComment));
        }
    }
}
