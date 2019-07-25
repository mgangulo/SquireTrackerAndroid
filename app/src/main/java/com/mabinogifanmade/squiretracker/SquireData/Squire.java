package com.mabinogifanmade.squiretracker.SquireData;


public enum Squire implements SquireCommon{

    DAI{
        @Override
        public String getName() {
            return "Dai";
        }

        @Override
        public boolean hasHints() {
            return true;
        }

        @Override
        public String sequenceConvo() {
            return null;
        }

        @Override
        public String getSequenceHint() {
            return null;
        }

        @Override
        public int imageSquire() {
            return 0;
        }
    },

    ELSIE{
        @Override
        public String getName() {
            return "Elsie";
        }

        @Override
        public boolean hasHints() {
            return false;
        }

        @Override
        public String sequenceConvo() {
            return null;
        }

        @Override
        public String getSequenceHint() {
            return null;
        }

        @Override
        public int imageSquire() {
            return 0;
        }
    },

    EIRLYS{
        @Override
        public String getName() {
            return "Eirlys";
        }

        @Override
        public boolean hasHints() {
            return true;
        }

        @Override
        public String sequenceConvo() {
            return null;
        }

        @Override
        public String getSequenceHint() {
            return null;
        }

        @Override
        public int imageSquire() {
            return 0;
        }
    },

    KAOUR{
        @Override
        public String getName() {
            return "Kaour";
        }

        @Override
        public boolean hasHints() {
            return false;
        }

        @Override
        public String sequenceConvo() {
            return null;
        }

        @Override
        public String getSequenceHint() {
            return null;
        }

        @Override
        public int imageSquire() {
            return 0;
        }
    }
}
