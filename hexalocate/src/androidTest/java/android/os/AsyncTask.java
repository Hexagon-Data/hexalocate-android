package android.os;

import java.util.concurrent.ExecutionException;

/**
 * This is a shadow class for AsyncTask which forces it to run synchronously.
 */
public abstract class AsyncTask<Params, Progress, Result> {

    Result result;

    protected abstract Result doInBackground(Params... params);

    protected void onPostExecute(Result result) {
    }

    protected void onProgressUpdate(Progress... values) {
    }

    public AsyncTask<Params, Progress, Result> execute(Params... params) {
        result = doInBackground(params);
        onPostExecute(result);
        return this;
    }

    public Result get() throws ExecutionException, InterruptedException {
        return result;
    }
}