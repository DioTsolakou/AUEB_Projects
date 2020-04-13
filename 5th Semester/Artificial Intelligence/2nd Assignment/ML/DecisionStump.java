package ML;

public class DecisionStump
{
    double errorLimit;
    double weightedError;

    public DecisionStump(){}

    public DecisionStump(double errorLimit, double weightedError)
    {
        this.errorLimit = errorLimit;
        this.weightedError = weightedError;
    }

    public double getErrorLimit()
    {
        return errorLimit;
    }

    public void setErrorLimit(double errorLimit)
    {
        this.errorLimit = errorLimit;
    }

    public double getWeightedError()
    {
        return weightedError;
    }

    public void setWeightedError(double weightedError)
    {
        this.weightedError = weightedError;
    }

    public int classifier(double value)
    {
        if (value > errorLimit)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
