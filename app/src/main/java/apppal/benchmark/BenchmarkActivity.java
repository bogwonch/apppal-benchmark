package apppal.benchmark;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import apppal.logic.evaluation.AC;
import apppal.logic.evaluation.Evaluation;
import apppal.logic.evaluation.Result;
import apppal.logic.language.Assertion;

public class BenchmarkActivity extends ActionBarActivity
{
  private String policy;

  private final Stopwatch timer = new Stopwatch();
  private long parsing_time;
  private long preprocessing_time;
  private long checking_time;

  private TextView parseText;
  private TextView preprocessText;
  private TextView checkText;

  private final Handler handler = new Handler();
  private final Runnable runBenchmark = new Runnable() {
    @Override
    public void run()
    {
      try
      {
        timer.start();
        final AC ac = new AC(policy);
        parsing_time = timer.stop();
        timer.reset();

        parseText.append(String.valueOf(parsing_time / 1000.0));

        List<Assertion> queries = getQueries(1);

        timer.start();
        Evaluation eval = new Evaluation(ac);
        preprocessing_time = timer.stop();
        timer.reset();

        preprocessText.append(String.valueOf(preprocessing_time / 1000.0));

        timer.start();
        for (Assertion q : queries)
        {
          Result r = eval.run(q);
          Toast.makeText(getApplicationContext(), r.toString(), Toast.LENGTH_SHORT).show();
        }
        checking_time = timer.stop();

        checkText.append(String.valueOf(checking_time / 1000.0));
      } catch (IOException err)
      {
        Toast.makeText(getApplicationContext(), "Error: " + err, Toast.LENGTH_LONG).show();
      }
    }

    private List<Assertion> getQueries(final int number)
    {
      final PackageManager pm = getPackageManager();
      final List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

      final ArrayList<Assertion> queries = new ArrayList<>();
      int n = number;
      for (ApplicationInfo p : packages)
      {
        if (n <= 0) break;
        final String name = p.packageName;
        if ((name.startsWith("com.google.") ||
          name.startsWith("com.android") ||
          name.startsWith("android.")))
          continue;

        try
        {
          queries.add(Assertion.parse("'user' says 'apk://"+name+"' isInstallable."));
          n -= 1;
        } catch (IOException err)
        {
          Toast.makeText(getApplicationContext(), "Parsing Error: " + err, Toast.LENGTH_LONG).show();
        }
      }

      if (queries.size() != number)
        Toast.makeText(getApplicationContext(), "Warning: not enough packages (" + queries.size() + ")", Toast.LENGTH_LONG).show();

      return queries;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_benchmark);

    final Intent intent = getIntent();
    policy = intent.getStringExtra(MainActivity.POLICY);

    parseText = (TextView) findViewById(R.id.parseText);
    preprocessText = (TextView) findViewById(R.id.preprocessText);
    checkText = (TextView) findViewById(R.id.checkText);
    handler.post(runBenchmark);
  }

  @Override
  public void onBackPressed()
  {
    handler.removeCallbacks(runBenchmark);
    super.onBackPressed();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_benchmark, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings)
    {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }


}
