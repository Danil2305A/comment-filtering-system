package org.stepik;

public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private final String[] keywords;

    public NegativeTextAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    protected String[] getKeywords() {
        return this.keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE;
    }
}
