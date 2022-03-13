package filters;

import models.ammunitions.Ammunition;

public interface Filter {
    public boolean check(Ammunition ammunition);
}
