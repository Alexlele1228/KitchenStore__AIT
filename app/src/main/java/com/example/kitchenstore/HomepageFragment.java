package com.example.kitchenstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kitchenstore.fragments.InviteUserFragment;
import com.example.kitchenstore.services.NotificationService;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomepageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomepageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomepageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomepageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomepageFragment newInstance(String param1, String param2) {
        HomepageFragment fragment = new HomepageFragment();
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


        return inflater.inflate(R.layout.homepage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //start listening for inventory changes
        Intent intent=new Intent(getContext(), NotificationService.class);
        getActivity().startService(intent);

        //...
        Button inventoryBtn, prepareMealBtn, expiredBtn, binBtn, inviteBtn, onlineStoreBtn;
        inventoryBtn=view.findViewById(R.id.inventory_button);
        expiredBtn=view.findViewById(R.id.expired_items_button_menu);
        binBtn=view.findViewById(R.id.bin_button_menu);
        prepareMealBtn=view.findViewById(R.id.prepare_meal_button_menu);
        inviteBtn=view.findViewById(R.id.invite_button_menu);
        onlineStoreBtn=view.findViewById(R.id.online_store_button_menu);

        inviteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.real_main_container, InviteUserFragment.newInstance("",""),"invite").addToBackStack("invite").commit();

            }
        });

        onlineStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(".store");
                startActivity(intent);
            }
        });
        inventoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 getFragmentManager().beginTransaction().replace(R.id.real_main_container,InventoryMenuOptionsFragment.newInstance("",""),"inventory").addToBackStack("inventory").commit();
            }
        });
        binBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BinFragment fragment= BinFragment.newInstance("binFrag","binFrag");
                //((MainActivity)getActivity()).changeFrag(fragment);
                getFragmentManager().beginTransaction().replace(R.id.real_main_container,fragment,"bin").addToBackStack("bin").commit();
            }
        });
        prepareMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getFragmentManager().beginTransaction().replace(R.id.real_main_container,PrepareAMealOptions.newInstance("",""),"prepare").addToBackStack("prepare").commit();
            }
        });
    }


}