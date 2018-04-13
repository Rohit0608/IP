package com.example.gamincoder.ip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Complaint_Card extends Fragment {

    private static Context con;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_complaint__card, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(con, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    con.startActivity(intent);
                }
            });

            // Adding Snackbar to Action Button inside card
            Button button = (Button)itemView.findViewById(R.id.action_button);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });

            ImageButton mailImageButton =
                    (ImageButton) itemView.findViewById(R.id.mail_button);
            mailImageButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Send Email",
                            Snackbar.LENGTH_LONG).show();
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","rohitgurijala@gmail.com;rai.utkarsh3099@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Issues in some of the hostel facilities ");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "This is hereby to notice you that");
                    con.startActivity(Intent.createChooser(emailIntent, "Send email via"));


                }
            });

            ImageButton smsImageButton = (ImageButton) itemView.findViewById(R.id.sms_button);
            smsImageButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Send Message",
                            Snackbar.LENGTH_LONG).show();
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.putExtra("address","9131900484;9140542802");
                    sendIntent.putExtra("sms_body", "This is hereby to inform you that");
                    sendIntent.setType("vnd.android-dir/mms-sms");
                    con.startActivity(sendIntent);


                }
            });
            ImageButton callImageButton = (ImageButton) itemView.findViewById(R.id.call_button);
            callImageButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Call",
                            Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+91-9131900484"));
                    con.startActivity(intent);



                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Card in RecyclerView.
        private static final int LENGTH = 5;

        private final String[] mProb;
        private final String[] mProbDesc;
        private final Drawable[] mProbPictures;

        public ContentAdapter(Context context) {
            con=context;
            Resources resources = context.getResources();
            mProb = resources.getStringArray(R.array.Prob);
            mProbDesc = resources.getStringArray(R.array.ProbDesc);
            TypedArray a = resources.obtainTypedArray(R.array.ProbPictures);
            mProbPictures = new Drawable[a.length()];
            for (int i = 0; i < mProbPictures.length; i++) {
                mProbPictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mProbPictures[position % mProbPictures.length]);
            holder.name.setText(mProb[position % mProb.length]);
            holder.description.setText(mProbDesc[position % mProbDesc.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
