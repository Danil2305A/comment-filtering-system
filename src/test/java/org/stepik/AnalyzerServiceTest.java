package org.stepik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnalyzerServiceTest {
    private AnalyzerService analyzerService;
    private TextAnalyzer[] analyzers;

    private String[] spamComments;
    private String[] negativeComments;
    private String[] tooLongComments;
    private String[] okComments;

    @BeforeEach
    public void setUp() {
        // Создаем анализаторы с конкретными ключевыми словами
        TextAnalyzer spamAnalyzer = new SpamAnalyzer(new String[] {
                "Без процентов", "Кредит", "Вклад", "Инвестиц", "Бонус", "Казино", "Ставк"
        });

        TextAnalyzer negativeAnalyzer = new NegativeTextAnalyzer(new String[] {
                "Туп", "Дебил", "Ублюд", "Мразь", "Заткнись", "Параша", "Чмо"
        });

        TextAnalyzer tooLongAnalyzer = new TooLongTextAnalyzer(70);

        analyzers = new TextAnalyzer[] { spamAnalyzer, negativeAnalyzer, tooLongAnalyzer };
        analyzerService = new AnalyzerService();

        // Тестовые комментарии
        spamComments = new String[] {
                "Кредит на 2 года без процентов и переплат",
                "Предлагаем выгодный вклад с большой ежемесячной ставкой",
                "Бонусы за регистрацию в нашем казино",
                "Инвестиционные предложения с гарантированной доходностью"
        };

        negativeComments = new String[] {
                "Ты что, совсем тупой?!",
                "Ну ты и дебил, конечно",
                "Не будь таким чмо",
                "Что за параша у тебя в голове?"
        };

        tooLongComments = new String[] {
                "Этот комментарий явно слишком длинный и превышает установленный лимит в семьдесят символов, поэтому он должен быть отклонен.",
                "Очень длинное сообщение с множеством деталей и подробностей, которое точно не поместится в ограниченное количество символов."
        };

        okComments = new String[] {
                "Какой милый котик!!!",
                "Спасибо за помощь!",
                "Интересная статья",
                "Согласен с автором"
        };
    }

    @Test
    @DisplayName("Тестирование проверки спам-комментариев через сервис")
    public void testSpamComments() {
        for (String spamComment : spamComments) {
            Assertions.assertEquals(Label.SPAM, analyzerService.checkLabels(analyzers, spamComment),
                    "Должен быть SPAM: \"" + spamComment + "\"");
        }
    }

    @Test
    @DisplayName("Тестирование проверки негативных комментариев через сервис")
    public void testNegativeComments() {
        for (String negativeComment : negativeComments) {
            Assertions.assertEquals(Label.NEGATIVE, analyzerService.checkLabels(analyzers, negativeComment),
                    "Должен быть NEGATIVE: \"" + negativeComment + "\"");
        }
    }

    @Test
    @DisplayName("Тестирование проверки слишком длинных комментариев через сервис")
    public void testTooLongComments() {
        for (String tooLongComment : tooLongComments) {
            Assertions.assertEquals(Label.TOO_LONG, analyzerService.checkLabels(analyzers, tooLongComment),
                    "Должен быть TOO_LONG: \"" + tooLongComment + "\"");
        }
    }

    @Test
    @DisplayName("Тестирование проверки нормальных комментариев через сервис")
    public void testOkComments() {
        for (String okComment : okComments) {
            Assertions.assertEquals(Label.OK, analyzerService.checkLabels(analyzers, okComment),
                    "Должен быть OK: \"" + okComment + "\"");
        }
    }

    @Test
    @DisplayName("Тестирование приоритета проверок (спам -> негатив -> длина)")
    public void testPriority() {
        // Комментарий с несколькими проблемами (спам, негатив, длина)
        String complexComment = "Кредит без процентов! Ты дебил! " +
                "И этот комментарий намеренно сделан очень длинным, чтобы превысить лимит.";

        Assertions.assertEquals(Label.SPAM, analyzerService.checkLabels(analyzers, complexComment),
                "При нескольких проблемах должен вернуть первую обнаруженную (SPAM)");
    }

    @Test
    @DisplayName("Тестирование пустого массива анализаторов")
    public void testEmptyAnalyzers() {
        TextAnalyzer[] emptyAnalyzers = new TextAnalyzer[0];
        Assertions.assertEquals(Label.OK, analyzerService.checkLabels(emptyAnalyzers, "Любой текст"),
                "При пустом массиве анализаторов всегда должен быть OK");
    }

    @Test
    @DisplayName("Тестирование пустого комментария")
    public void testEmptyComment() {
        Assertions.assertEquals(Label.OK, analyzerService.checkLabels(analyzers, ""),
                "При пустом комментарии должен быть OK");
    }
}
