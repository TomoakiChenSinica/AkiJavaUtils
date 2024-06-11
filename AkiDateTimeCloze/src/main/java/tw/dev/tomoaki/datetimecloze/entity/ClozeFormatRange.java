package tw.dev.tomoaki.datetimecloze.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tomoaki Chen
 */
public final class ClozeFormatRange {

    private String startClozeFormat;
    private String endClozeFormat;

    public static class Factory {

        public static ClozeFormatRange create(String startClozeFormat, String endClozeFormat) {
            ClozeFormatRange range = new ClozeFormatRange();
            range.startClozeFormat = startClozeFormat;
            range.endClozeFormat = endClozeFormat;
            return range;
        }
    }

    public String getStartClozeFormat() {
        return startClozeFormat;
    }

    public void setStartClozeFormat(String startClozeFormat) {
        this.startClozeFormat = startClozeFormat;
    }

    public String getEndClozeFormat() {
        return endClozeFormat;
    }

    public void setEndClozeFormat(String endClozeFormat) {
        this.endClozeFormat = endClozeFormat;
    }

    @Override
    public String toString() {
        return String.format("%s ~ %s", startClozeFormat, endClozeFormat);
    }
}
