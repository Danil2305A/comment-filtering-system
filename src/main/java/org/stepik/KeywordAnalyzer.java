package org.stepik;

public abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();
    protected abstract Label getLabel();

    @Override
    public Label processText(String text) {
        String[] keywords = this.getKeywords();

        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return this.getLabel();
            }
        }

        return Label.OK;
    }
}
