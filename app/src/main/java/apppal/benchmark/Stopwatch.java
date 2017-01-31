package apppal.benchmark;

import static android.os.SystemClock.elapsedRealtime;

/**
 * Implements a stopwatch for timing the real time.
 */
public class Stopwatch
{
  private long started;
  private long accumulated;

  public Stopwatch()
  {
    this.started = 0;
    this.accumulated = 0;
  }
  public void start()
  {
    this.started = elapsedRealtime();
  }

  public long stop()
  {
    this.accumulated = this.check();
    this.started = 0;

    return this.accumulated;
  }

  public void reset()
  {
    this.started = 0;
    this.accumulated = 0;
  }

  public long check()
  {
    if (this.started < 0)
      return 0;
    else
      return elapsedRealtime() - this.started + this.accumulated;
  }
}
