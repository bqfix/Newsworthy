package com.example.android.newsworthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NewsStoriesAdapter extends RecyclerView.Adapter<NewsStoriesAdapter.NewsStoriesAdapterViewHolder> {

    private List<NewsStory> mNewsStories;

    private final NewsStoriesClickHandler mNewsStoriesClickHandler;

    //Constructor
    public NewsStoriesAdapter(NewsStoriesClickHandler newsStoriesClickHandler) {
        this.mNewsStoriesClickHandler = newsStoriesClickHandler;
    }

    //Inner ViewHolder class for the RecyclerAdapter
    public class NewsStoriesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Views in a News List Item
        TextView mTitleView;
        TextView mDateView;
        TextView mSectionView;

        public NewsStoriesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitleView = (TextView) itemView.findViewById(R.id.title_text_view);
            mDateView = (TextView) itemView.findViewById(R.id.date_text_view);
            mSectionView = (TextView) itemView.findViewById(R.id.section_text_view);

            itemView.setOnClickListener(this);
        }

        //On Click, process the click by passing the NewsStory to the below interface
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            NewsStory newsStory = mNewsStories.get(adapterPosition);
            mNewsStoriesClickHandler.onItemClick(newsStory);

        }
    }

    @NonNull
    @Override
    public NewsStoriesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new NewsStoriesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsStoriesAdapterViewHolder newsStoriesAdapterViewHolder, int position) {
        NewsStory newsStory = mNewsStories.get(position);

        newsStoriesAdapterViewHolder.mTitleView.setText(newsStory.getTitle());
        newsStoriesAdapterViewHolder.mDateView.setText(newsStory.getFormattedDate());
        newsStoriesAdapterViewHolder.mSectionView.setText(newsStory.getSection());
    }

    @Override
    public int getItemCount() {
        if (mNewsStories == null) return 0;
        return mNewsStories.size();
    }

    //Interface to handle clicks, defined in MainActivity
    public interface NewsStoriesClickHandler {
        void onItemClick(NewsStory newsStory);
    }

    /**
     * Helper method to set NewsStories to existing NewsStoriesAdapter to prevent creating a new one
     *
     * @param newsStories the new set of NewsStories to be displayed
     */

    public void setNewsStories(List<NewsStory> newsStories) {
        mNewsStories = newsStories;
        notifyDataSetChanged();
    }

}
