package com.mysql.jdbc;

import com.mysql.jdbc.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Iterator;

public abstract class IterateBlock<T> {
    DatabaseMetaData.IteratorWithCleanup<T> iteratorWithCleanup;
    Iterator<T> javaIterator;
    boolean stopIterating = false;

    /* access modifiers changed from: package-private */
    public abstract void forEach(T t) throws SQLException;

    IterateBlock(DatabaseMetaData.IteratorWithCleanup<T> i) {
        this.iteratorWithCleanup = i;
        this.javaIterator = null;
    }

    IterateBlock(Iterator<T> i) {
        this.javaIterator = i;
        this.iteratorWithCleanup = null;
    }

    public void doForAll() throws SQLException {
        if (this.iteratorWithCleanup != null) {
            do {
                try {
                    if (!this.iteratorWithCleanup.hasNext()) {
                        break;
                    }
                    forEach(this.iteratorWithCleanup.next());
                } finally {
                    this.iteratorWithCleanup.close();
                }
            } while (!this.stopIterating);
            return;
        }
        while (this.javaIterator.hasNext()) {
            forEach(this.javaIterator.next());
            if (this.stopIterating) {
                return;
            }
        }
    }

    public final boolean fullIteration() {
        return !this.stopIterating;
    }
}
