package ba.sum.fsre.blagajna.model;

public enum Kategorija {
    TOPLI("Topli napitci"),
    BEZA("Bezalkoholna piće"),
    ALKOHOL("Alkoholna pića"),
    PIVA("Piva"),
    KOKTELI("Kokteli"),
    VINA("Vina");

    private String displayName;

    Kategorija(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
