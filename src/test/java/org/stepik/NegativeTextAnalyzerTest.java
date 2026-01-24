package org.stepik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NegativeTextAnalyzerTest {
    private NegativeTextAnalyzer negativeAnalyzer;
    private String[] negativeKeywords;
    private String[] negativeComments;
    private String[] notNegativeComments;

    @BeforeEach
    public void setUp() {
        negativeKeywords = new String[] {
                "Туп",
                "Дебил",
                "Ублюд",
                "Мразь",
                "Заткнись",
                "Параша",
                "Чмо"
        };

        negativeComments = new String[] {
                "Ты что, совсем тупой?!",
                "Ну ты и дебил, конечно",
                "Веди себя нормально, ублюдок!",
                "Он настоящая мразь",
                "Заткнись уже, надоел!",
                "Что за параша у тебя в голове?",
                "Не будь таким чмо"
        };

        notNegativeComments = new String[] {
                "Кредит на 2 года без процентов и переплат",
                "Предлагаем выгодный вклад с большой ежемесячной ставкой",
                "Сериал на любителя, в целом можно смотреть",
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF",
                "Интересная статья о программировании",
                "Когда будет следующее собрание?"
        };

        negativeAnalyzer = new NegativeTextAnalyzer(negativeKeywords);
    }

    @Test
    @DisplayName("Тестирование проверки комментариев на негатив")
    public void testCommentsIsNegative() {
        for (String negativeComment : negativeComments) {
            Assertions.assertEquals(Label.NEGATIVE, negativeAnalyzer.processText(negativeComment));
        }
    }

    @Test
    @DisplayName("Тестирование проверки комментариев не на негатив")
    public void testCommentIsNotNegative() {
        for (String notNegativeComment : notNegativeComments) {
            Assertions.assertNotEquals(Label.NEGATIVE, negativeAnalyzer.processText(notNegativeComment));
        }
    }
}