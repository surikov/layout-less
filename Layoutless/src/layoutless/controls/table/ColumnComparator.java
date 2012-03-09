package layoutless.controls.table;

import java.util.*;

public class ColumnComparator implements Comparator<SimpleColumn> {
    @Override
    public int compare(SimpleColumn o1, SimpleColumn o2) {
	int n1 = 0;
	int n2 = 0;
	if (o1.order.property.value() != null) {
	    n1 = o1.order.property.value().intValue();
	}
	if (o2.order.property.value() != null) {
	    n2 = o2.order.property.value().intValue();
	}
	if (n1 > n2) {
	    return +1;
	}
	else {
	    return -1;
	}
    }
}
