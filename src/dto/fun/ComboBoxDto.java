package dto.fun;

import dto.Dto;

public class ComboBoxDto implements Dto {
    private String tableName;

    public ComboBoxDto(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public int getId() {
        return 0;
    }
}
