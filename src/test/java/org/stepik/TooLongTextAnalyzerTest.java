package org.stepik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TooLongTextAnalyzerTest {
    private TooLongTextAnalyzer tooLongAnalyzer;
    private int maxLength;
    private String[] tooLongComments;
    private String[] notTooLongComments;

    @BeforeEach
    public void setUp() {
        maxLength = 70;
        tooLongAnalyzer = new TooLongTextAnalyzer(maxLength);

        tooLongComments = new String[] {
                "Этот комментарий явно слишком длинный и превышает установленный лимит в семьдесят символов, поэтому он должен быть отклонен.",
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", // 93 символов
                "Очень длинное сообщение с множеством деталей и подробностей, которое точно не поместится в ограниченное количество символов и будет признано слишком длинным для публикации на нашей платформе.",
                "12345678901234567890123456789012345678901234567890123456789012345678901" // 71 символ
        };

        notTooLongComments = new String[] {
                "Короткий комментарий",
                "Ты что, совсем тупой?!",
                "Кредит на 2 года без процентов",
                "Сериал на любителя",
                "123456789012345678901234567890123456789012345678901234567890123456789", // 69 символов
                "Ровно семьдесят символов должно быть в этом сообщении для проверки!!!!" // 70 символов
        };
    }

    @Test
    @DisplayName("Тестирование проверки комментариев на слишком большую длину")
    public void testCommentsIsTooLong() {
        for (String tooLongComment : tooLongComments) {
            Assertions.assertEquals(Label.TOO_LONG, tooLongAnalyzer.processText(tooLongComment));
        }
    }

    @Test
    @DisplayName("Тестирование проверки комментариев не на слишком большую длину")
    public void testCommentIsNotTooLong() {
        for (String notTooLongComment : notTooLongComments) {
            Assertions.assertNotEquals(Label.TOO_LONG, tooLongAnalyzer.processText(notTooLongComment));
        }
    }
}
