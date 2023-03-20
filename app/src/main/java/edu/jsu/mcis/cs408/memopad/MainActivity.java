package edu.jsu.mcis.cs408.memopad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;

import edu.jsu.mcis.cs408.memopad.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        db = new DatabaseHandler(this, null, null, 1);
        updateMemoList();

        binding.addMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMemo();
            }
        });

        binding.deleteMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                deleteMemo();
            }
        });

    }

    public void addMemo() {
        String message = binding.memoTitle.getText().toString();
        db.addMemo(message);
        updateMemoList();
    }

    public void deleteMemo() {
        String ID = binding.deleteMemoInput.getText().toString();
        db.deleteMemo(ID);
        updateMemoList();
    }

    private void updateMemoList() {
        RecyclerViewAdapter adaptor = new RecyclerViewAdapter(db.getMemosAsList());
        binding.memoList.setHasFixedSize(true);
        binding.memoList.setLayoutManager(new LinearLayoutManager(this));
        binding.memoList.setAdapter(adaptor);
    }
}