package by.vsu.filters;

import by.vsu.models.ammunitions.Ammunition;

public interface Filter {
    public boolean check(Ammunition ammunition);
}
