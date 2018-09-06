/*
 * Copyright 2004-2018 H2 Group. Multiple-Licensed under the MPL 2.0,
 * and the EPL 1.0 (http://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.h2.result;

import org.h2.engine.SessionInterface;
import org.h2.value.Value;

/**
 * A local result set contains all row data of a result set.
 * This is the object generated by engine,
 * and it is also used directly by the ResultSet class in the embedded mode.
 * If the result does not fit in memory, it is written to a temporary file.
 */
public interface LocalResult extends ResultInterface, ResultTarget {
    /**
     * @param maxValue
     */
    public void setMaxMemoryRows(int maxValue);

    public LocalResult createShallowCopy(SessionInterface targetSession);

    /**
     * @param sort Sort order.
     */
    public void setSortOrder(SortOrder sort);

    /**
     * Remove duplicate rows.
     */
    public void setDistinct();

    /**
     * Remove rows with duplicates in columns with specified indexes.
     *
     * @param distinctIndexes distinct indexes
     */
    public void setDistinct(int[] distinctIndexes);

    /**
     * Remove the row from the result set if it exists.
     *
     * @param values the row
     */
    public void removeDistinct(Value[] values);

    /**
     * This method is called after all rows have been added.
     */
    public void done();

    /**
     * Set the number of rows that this result will return at the maximum.
     *
     * @param limit the limit (-1 means no limit, 0 means no rows)
     */
    public void setLimit(int limit);

    /**
     * @param fetchPercent whether limit expression specifies percentage of rows
     */
    public void setFetchPercent(boolean fetchPercent);

    /**
     * @param withTies whether tied rows should be included in result too
     */
    public void setWithTies(boolean withTies);

    /**
     * Set the offset of the first row to return.
     *
     * @param offset the offset
     */
    public void setOffset(int offset);
}
