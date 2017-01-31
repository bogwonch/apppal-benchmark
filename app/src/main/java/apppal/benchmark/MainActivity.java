package apppal.benchmark;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity
{
  public final static String POLICY = "apppal.benchmark.POLICY";
  public static Context instance;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    instance = this;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
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

  public void onClickButton_1_10(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_1_10));
    startActivity(intent);
  }

  public void onClickButton_1_100(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_1_100));
    startActivity(intent);
  }

  public void onClickButton_1_500(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_1_500));
    startActivity(intent);
  }

  public void onClickButton_1_1000(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_1_1000));
    startActivity(intent);
  }

  public void onClickButton_2_10(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_2_10));
    startActivity(intent);
  }

  public void onClickButton_2_100(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_2_100));
    startActivity(intent);
  }

  public void onClickButton_2_500(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_2_500));
    startActivity(intent);
  }

  public void onClickButton_2_1000(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_2_1000));
    startActivity(intent);
  }

  public void onClickButton_3_10(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_3_10));
    startActivity(intent);
  }

  public void onClickButton_3_100(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_3_100));
    startActivity(intent);
  }

  public void onClickButton_3_500(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_3_500));
    startActivity(intent);
  }

  public void onClickButton_3_1000(View view)
  {
    Intent intent = new Intent(this, BenchmarkActivity.class);
    intent.putExtra(POLICY, getString(R.string.policy_3_1000));
    startActivity(intent);
  }
}
