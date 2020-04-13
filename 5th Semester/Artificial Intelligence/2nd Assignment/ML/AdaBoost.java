package ML;

import java.util.List;
import java.util.Vector;
import java.math.*;

public class AdaBoost
{
    public List<String> examples;
    int M;
    public Vector<Double> weights;
    public Vector<Integer> h;
    public Vector<Double> z;

    public AdaBoost() {}

    public AdaBoost(List<String> examples, int M, Vector<Double> weights, Vector<Integer> h, Vector<Double> z)
    {
        this.examples = examples;
        this.M = M;
        this.weights = weights;
        this.h = h;
        this.z = z;
    }

    public double AdaBoost(double errorLimit, double weightedError)
    {
        weights.setSize(examples.size());
        double error;
        double y = Math.random(); //dummy value

        DecisionStump decisionStump = new DecisionStump(errorLimit, weightedError);

        for (int i = 0; i < weights.size(); i++)
        {
            weights.set(i, (double) 1/weights.size());
        }

        for (int i = 0; i < M; i++)
        {
            h.set(i, decisionStump.classifier(Double.parseDouble(examples.get(i))));
            error = 0;

            for (int j = 0; j < examples.size(); j++)
            {
                if (h.get(i) != y)
                {
                    error += weights.get(j);
                }
            }

            for (int j = 0; j < examples.size(); j++)
            {
                if (h.get(i) == y)
                {
                    weights.set(j, weights.get(j) * error / (1 - error));
                }
            }

            //normalize(weights);
            z.set(i, Math.log((1 - error) / error));
        }

        return 1.0; //dummy value
    }
}
