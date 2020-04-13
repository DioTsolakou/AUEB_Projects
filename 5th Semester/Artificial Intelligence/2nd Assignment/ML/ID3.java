package ML;

import java.util.List;
import java.util.TreeSet;

public class ID3
{
    public List<String> examples;
    public List<String> attributes;
    public String defaultCategory;
    public TreeSet<Node> S;

    public ID3(){}

    public ID3(List<String> examples, List<String> attributes, String defaultCategory, TreeSet<Node> S)
    {
        this.examples = examples;
        this.attributes = attributes;
        this.defaultCategory = defaultCategory;
        this.S = S;
    }

    public Node ID3(List<String> examples, List<String> attributes, String defaultCategory)
    {
        Node root = new Node(S.first());
        Node defaultNode = new Node(defaultCategory);

        if (examples.isEmpty())
        {
            return defaultNode;
        }

        int counter = 0;
        for (String example : examples)
        {
            if (example.equals("spam"))
            {
                counter++;
            }
        }

        if (counter == examples.size()) //all examples are in the same category
        {
            return new Node("spam"); //return that category
        }

        if (attributes.isEmpty())
        {
            return new Node("ham");//most frequent category
        }

        return new Node(null);
    }

   public double calculateEntropy(List<Word> dataset)
   {
       double entropy = 0;
       double probability = 0;
       int totalFrequency = 0;

       if (dataset.size() == 0)
       {
           return 0;
       }

       for (Word word : dataset)
       {
           totalFrequency += word.getFrequency();
       }

       for (Word word : dataset)
       {
           probability = word.getFrequency() / totalFrequency;
           entropy += -probability * log2(probability);
       }

       return entropy;
   }

   public double calculateEntropyAttribute(List<Word> dataset, Word attribute)
   {
       double entropy = 0;
       double probability = 0;
       int totalFrequency = 0;

       if (dataset.size() == 0)
       {
           return 0;
       }

       for (Word word : dataset)
       {
           totalFrequency += word.getFrequency();
       }

       probability = attribute.getFrequency() / totalFrequency;
       entropy = -probability * log2(probability);

       return entropy;
   }

   public double calculateInformationGain(double entropy, List<Double> subTreeEntropy, List<Word> dataset)
   {
       double IG = 0;

       IG = entropy;
       for (int i = 0; i < subTreeEntropy.size(); i++)
       {
           IG += - calculateEntropyAttribute(dataset, dataset.get(i));
       }

       return IG;
   }

    public static double log2(double n)
    {
        return Math.log(n) / Math.log(2);
    }
}
