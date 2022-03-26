package by.vsu.filters;

import by.vsu.models.ammunitions.Ammunition;

public interface Filter {
    boolean check(Ammunition ammunition);
}
