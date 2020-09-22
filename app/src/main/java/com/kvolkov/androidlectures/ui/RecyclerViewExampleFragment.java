package com.kvolkov.androidlectures.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kvolkov.androidlectures.LoadRecyclerViewDataAsyncTask;
import com.kvolkov.androidlectures.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class RecyclerViewExampleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private TextView mProgress;
    private List<String> mRandomDataList = new LinkedList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerViewAdapter mAdapter;
    private LoadRecyclerViewDataAsyncTask mAsyncTask;

    public interface ILoadProgress {
        void onLoadComplete();
        void onLoadProgress(int progress);
    }

    public RecyclerViewExampleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExampleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerViewExampleFragment newInstance(String param1, String param2) {
        RecyclerViewExampleFragment fragment = new RecyclerViewExampleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recyclerview_example, container, false);
        mRecyclerView = view.findViewById(R.id.recycler);
        mProgress = view.findViewById(R.id.progress);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RecyclerViewAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.addItem("Initial item", 0);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setupListener();
            }
        }, 500);
    }

    private void setupListener() {
        RecyclerViewAdapter.ViewHolder holder = (RecyclerViewAdapter.ViewHolder) mRecyclerView.findViewHolderForAdapterPosition(0);
        final ILoadProgress listener = new ILoadProgress() {
            @Override
            public void onLoadComplete() {
                // show data
                mProgress.setVisibility(View.INVISIBLE);
                mAdapter.setItems(mRandomDataList);
            }

            @Override
            public void onLoadProgress(int progress) {
                mProgress.setText(progress + "%");
            }
        };

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fun();

                mProgress.setVisibility(View.VISIBLE);
                mAsyncTask = new LoadRecyclerViewDataAsyncTask(mRandomDataList, listener);
                mAsyncTask.execute("param 1", "param 2");
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mAsyncTask.setListener(null);
        mAsyncTask.cancel(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void fun() {
        try {
            JSONObject obj = new JSONObject("{\"id\":10979,\"publication_date\":1437502873,\"title\":\"В Гатчине отменили фестиваль «Ночь света — 2015»\",\"slug\":\"v-gatchine-otmenili-festival-noch-sveta-2015\",\"place\":{\"id\":17645,\"title\":\"Музей-заповедник «Гатчина»\",\"slug\":\"muzej-zapovednik-gatchina\",\"address\":\"Ленинградская обл., г. Гатчина, просп. Красноармейский, д. 1\",\"phone\":\"+7 812 958-03-66, +7 813 719-34-92\",\"is_stub\":true,\"site_url\":\"https://kudago.com/spb/place/muzej-zapovednik-gatchina/\",\"coords\":{\"lat\":59.56395871546711,\"lon\":30.10800637574349},\"subway\":\"\",\"is_closed\":false,\"location\":\"spb\"},\"description\":\"<p>Руководство музея-заповедника «Гатчина» сообщило о том, что праздника света в этом году не будет.</p>\\n\",\"body_text\":\"<p>Анонсированная ранее <a href=\\\"http://kudago.com/spb/event/sveto-pirotehnicheskoe-shou-noch-sveta-v-gatchine/\\\">«Ночь света в Гатчине — 2015»</a> должна была стать грандиозным зрелищем. Были запланированы мультимедийное лазерное шоу, фейерверки, инсталляции и театрализованная программа. К сожалению, мероприятие отменили.</p>\\n<p>В официальной группе руководство музея-заповедника дает следующее объяснение: «Мы решили взять паузу и лучше продумать формат. Надеемся вернуться с обновленным мероприятием в 2016 году».</p>\\n<p>Информацию, появившуюся до этого в интернете, в музее прокомментировали так: «Обращаем ваше внимание на то, что все анонсы фестиваля этого года в сети Интернет размещены без согласования с музеем-заповедником \\\"Гатчина\\\"».</p>\\n\",\"images\":[{\"image\":\"https://kudago.com/media/images/news/a9/0e/a90ed21e406357e0ff12e1128c775b9b.jpg\",\"thumbnails\":{\"640x384\":\"https://kudago.com/media/thumbs/640x384/images/news/a9/0e/a90ed21e406357e0ff12e1128c775b9b.jpg\",\"144x96\":\"https://kudago.com/media/thumbs/144x96/images/news/a9/0e/a90ed21e406357e0ff12e1128c775b9b.jpg\"},\"source\":{\"name\":\"vk.com\",\"link\":\"http://vk.com/album-55848808_176736434\"}}],\"site_url\":\"https://kudago.com/spb/news/v-gatchine-otmenili-festival-noch-sveta-2015/\",\"favorites_count\":0,\"comments_count\":0,\"disable_comments\":false}");

            obj.getBoolean("disable_comments");
            JSONObject placeObj = obj.getJSONObject("place");
            placeObj.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}