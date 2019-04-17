package hibernate.dao;

import hibernate.entity.Chip;

import java.util.List;

public interface ChipDao {
    void save(Chip chip);
    void delete(Integer id);
    Chip getChip(Integer id);
    List<Chip> getAll();
}
