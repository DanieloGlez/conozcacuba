package dto.nomenclators;

public abstract class NomenclatorDto {
    protected String id;
    protected String name;

    // Constructors
    public NomenclatorDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Overridden Methods
    @Override
    public boolean equals(Object obj) {
        boolean areEquals = false;

        if (this.getClass() == obj.getClass()) {
            NomenclatorDto nomenclatorDto = (NomenclatorDto) obj;
            areEquals = id.equals(nomenclatorDto.id) && name.equals(nomenclatorDto.name);
        }

        return areEquals;
    }

    @Override
    public String toString() {
        return name;
    }
}
