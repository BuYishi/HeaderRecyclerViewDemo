package com.bu_ish.headerrecyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChapterHeaderRecyclerViewAdapter extends HeaderRecyclerViewAdapter<ChapterHeaderRecyclerViewAdapter.HeaderViewHolder, ChapterHeaderRecyclerViewAdapter.SectionViewHolder> {
    private List<CourseChaptersData.Chapter> chapters;

    ChapterHeaderRecyclerViewAdapter(List<CourseChaptersData.Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(@NonNull ViewGroup parent) {
        return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter_header, parent, false));
    }

    @Override
    public SectionViewHolder onCreateSectionViewHolder(@NonNull ViewGroup parent) {
        return new SectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter_section, parent, false));
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder holder, int position) {
        CourseChaptersData.Chapter chapter = chapters.get(position);
        holder.tvHeader.setText(chapter.getName());
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder holder, int headerPosition, int sectionPosition) {
        CourseChaptersData.Chapter.Section section = chapters.get(headerPosition).getSections().get(sectionPosition);
        holder.tvSection.setText(section.getName());
    }

    @Override
    public int getHeaderItemCount() {
        return chapters.size();
    }

    @Override
    public int getSectionItemCount(int headerPosition) {
        return chapters.get(headerPosition).getSections().size();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHeader;

        private HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
        }
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSection;

        private SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSection = itemView.findViewById(R.id.tvSection);
        }
    }
}
