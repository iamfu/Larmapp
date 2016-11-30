package com.iamfu.larmapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.iamfu.larmapp.databinding.HomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements DetailFragment.OnFragmentInteractionListener {

    private HomeBinding binding;
    private TextToSpeech tts;

    private int[] tabIcons = {
            R.drawable.ic_tab_larm,
            R.drawable.ic_tab_dictionary,
            R.drawable.ic_tab_lesson,
            R.drawable.ic_tab_menu
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.home);
        setupViewPager(binding.viewpager);

        binding.tabs.setupWithViewPager(binding.viewpager);
        setupTabIcons();
// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setupTabIcons() {
        binding.tabs.getTabAt(0).setIcon(tabIcons[0]);
        binding.tabs.getTabAt(1).setIcon(tabIcons[1]);
        binding.tabs.getTabAt(2).setIcon(tabIcons[2]);
        binding.tabs.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new LarmFragment(), getString(R.string.larm_th));
        adapter.addFrag(new DictionaryFragment(), getString(R.string.dictionary_th));
        adapter.addFrag(new LessonFragment(), getString(R.string.lesson_th));
        adapter.addFrag(new MenuFragment(), getString(R.string.menu_th));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Home Page", // TODO: Define a title for the content shown.
// TODO: If you have web page content that matches this app activity's content,
// make sure this auto-generated web page URL is correct.
// Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
// TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.iamfu.larmapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Home Page", // TODO: Define a title for the content shown.
// TODO: If you have web page content that matches this app activity's content,
// make sure this auto-generated web page URL is correct.
// Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
// TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.iamfu.larmapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    @Override
    public void onFragmentInteraction(String name, String value) {

        switch (name) {

            case "DETAIL":
                break;

            case "TTS":

                if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    tts.speak(value, TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    tts.speak(value, TextToSpeech.QUEUE_FLUSH, null);
                }

                break;
        }

    }

    public static class LarmFragment extends Fragment {
        private final int SPEECH_RECOGNITION_CODE = 1;
        private View rootView;
        private TextView txtOutput;
        private ImageView btnMicrophone;
        private ImageView btnSwitchLang;

        private Switch aSwitch;

        private Spinner inputLang;
        private Spinner outputLang;

        private String inputText;
        private String outputText;


        public LarmFragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_larm, container,
                    false);

            //set spinner
            inputLang = (Spinner) rootView.findViewById(R.id.inputLang);
            outputLang = (Spinner) rootView.findViewById(R.id.outputLang);
            btnSwitchLang = (ImageView) rootView.findViewById(R.id.btnSwitchLang);

            btnSwitchLang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String i = inputLang.getSelectedItem().toString();
                    switch (i) {
                        case "ภาษาอังกฤษ":
                            outputLang.setSelection(1);
                            inputLang.setSelection(1);
                            break;
                        case "ภาษาไทย":
                            outputLang.setSelection(0);
                            inputLang.setSelection(0);
                            break;
                    }
                }
            });

            inputLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String i = inputLang.getSelectedItem().toString();
                    ArrayAdapter inputAdap = (ArrayAdapter) inputLang.getAdapter();
                    int spinnerPosition = inputAdap.getPosition(i);
                    switch (position) {
                        case 0:
                            outputLang.setSelection(0);
                            break;
                        case 1:
                            outputLang.setSelection(1);
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            outputLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String i = outputLang.getSelectedItem().toString();
                    ArrayAdapter outputAdap = (ArrayAdapter) outputLang.getAdapter();
                    int spinnerPosition = outputAdap.getPosition(i);
                    switch (position) {
                        case 0:
                            inputLang.setSelection(0);
                            break;
                        case 1:
                            inputLang.setSelection(1);
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            aSwitch = (Switch) rootView.findViewById(R.id.suggestSwitch);

            txtOutput = (TextView) rootView.findViewById(R.id.txt_output);
            btnMicrophone = (ImageView) rootView.findViewById(R.id.btnMicrophone);
            btnMicrophone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputText = changeLangInput(inputLang.getSelectedItem().toString());
                    outputText = changeLangOutput(outputLang.getSelectedItem().toString());

                    if (inputText.equals(outputText)) {
                        Toast.makeText(getContext(), R.string.lang_alert, Toast.LENGTH_LONG).show();
                    } else {
                        startSpeechToText();
                    }

                }
            });

            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                }
            });

// Inflate the layout for this fragment
            return rootView;
        }

        /**
         * Start speech to text intent. This opens up Google Speech Recognition API dialog box to listen the speech input.
         */
        private void startSpeechToText() {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            switch (inputLang.getSelectedItem().toString()) {
                case "ภาษาอังกฤษ":
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US);
                    break;
                case "ภาษาไทย":
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "th-TH");
                    break;
            }
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                    "Talk something...");

            try {
                startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
            } catch (ActivityNotFoundException a) {
                Toast.makeText(this.getContext(),
                        "Sorry! Speech recognition is not supported in this device.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Callback for speech recognition activity
         */
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case SPEECH_RECOGNITION_CODE: {
//if (resultCode == RESULT_OK && null != data) {
                    if (null != data) {
                        ArrayList<String> result = data
                                .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        String text = result.get(0);
                        txtOutput.setText(text);

                        Intent myIntent = new Intent(getActivity(), TranslateActivity.class);
                        myIntent.putExtra("plainText", text);
                        myIntent.putExtra("suggestCheck", aSwitch.isChecked());
                        myIntent.putExtra("inputText", inputText);
                        myIntent.putExtra("outputText", outputText);
                        startActivity(myIntent);
                    }
                    break;
                }
            }
        }

        public String changeLangInput(String lang) {
            switch (lang) {
                case "ภาษาอังกฤษ":
                    lang = "en";
                    break;
                case "ภาษาไทย":
                    lang = "th";
                    break;
            }
            return lang;
        }

        public String changeLangOutput(String lang) {
            switch (lang) {
                case "ภาษาอังกฤษ":
                    lang = "en";
                    break;
                case "ภาษาไทย":
                    lang = "th";
                    break;
            }
            return lang;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}