package com.bu_ish.headerrecyclerviewdemo;

import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class HeaderRecyclerViewAdapter<HVH extends RecyclerView.ViewHolder, SVH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    public abstract HVH onCreateHeaderViewHolder(@NonNull ViewGroup parent);

    public abstract SVH onCreateSectionViewHolder(@NonNull ViewGroup parent);

    public abstract void onBindHeaderViewHolder(HVH holder, int position);

    public abstract void onBindSectionViewHolder(SVH holder, int headerPosition, int sectionPosition);

    public abstract int getHeaderItemCount();

    public abstract int getSectionItemCount(int headerPosition);

    private int itemCount = INVALID_VALUE;
    private SparseBooleanArray itemTypes;
    private SparseIntArray headerPositions, sectionPositions;
    private static final int INVALID_VALUE = -1;
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_SECTION = 1;
    private static final int POSITION_TYPE_HEADER = 0;
    private static final int POSITION_TYPE_SECTION = 1;

    HeaderRecyclerViewAdapter() {
        headerPositions = new SparseIntArray();
        sectionPositions = new SparseIntArray();
    }

    private boolean isHeader(int position) {
        if (itemTypes == null) {
            itemTypes = new SparseBooleanArray();
            itemTypes.put(0, true);
            for (int headerPosition = 0, count = getHeaderItemCount(), headerRawPosition = 0; headerPosition < count; ++headerPosition) {
                headerRawPosition += getSectionItemCount(headerPosition) + 1;
                itemTypes.put(headerRawPosition, true);
            }
            return itemTypes.get(position);
        } else {
            return itemTypes.get(position);
        }
    }

    private int getHeaderPosition(int rawPosition) {
        return getPosition(rawPosition, POSITION_TYPE_HEADER);
    }

    private int getSectionPosition(int rawPosition) {
        return getPosition(rawPosition, POSITION_TYPE_SECTION);
    }

    private int getPosition(int rawPosition, int positionType) {
        int position;
        if (positionType == POSITION_TYPE_HEADER) {
            position = headerPositions.get(rawPosition, INVALID_VALUE);
        } else {
            position = sectionPositions.get(rawPosition, INVALID_VALUE);
        }
        if (position == INVALID_VALUE) {
            for (int headerIndex = 0, headerCount = getHeaderItemCount(), beginAnchor = 0; headerIndex < headerCount; ++headerIndex) {
                int sectionCount = getSectionItemCount(headerIndex);
                int endAnchor = beginAnchor + sectionCount + 1;
                if (rawPosition >= beginAnchor && rawPosition < endAnchor) {
                    if (positionType == POSITION_TYPE_HEADER) {
                        headerPositions.put(rawPosition, headerIndex);
                        return headerIndex;
                    } else {
                        position = rawPosition - beginAnchor - 1;
                        sectionPositions.put(rawPosition, position);
                        return position;
                    }
                }
                beginAnchor = endAnchor;
            }
        }
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return ITEM_VIEW_TYPE_HEADER;
        }
        return ITEM_VIEW_TYPE_SECTION;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            return onCreateHeaderViewHolder(parent);
        }
        return onCreateSectionViewHolder(parent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isHeader(position)) {
            onBindHeaderViewHolder((HVH) holder, getHeaderPosition(position));
        } else {
            onBindSectionViewHolder((SVH) holder, getHeaderPosition(position), getSectionPosition(position));
        }
    }

    @Override
    public int getItemCount() {
        if (itemCount == INVALID_VALUE) {
            int headerCount = getHeaderItemCount(), totalSectionCount = 0;
            for (int headerIndex = 0; headerIndex < headerCount; ++headerIndex) {
                totalSectionCount += getSectionItemCount(headerIndex);
            }
            return itemCount = headerCount + totalSectionCount;
        }
        return itemCount;
    }
}
