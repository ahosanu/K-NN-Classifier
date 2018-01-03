package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class kNN_Classifier {
	int exampleCount;
	int featureCount;
	int classCount;
	int[][] F; // Features of the training examples
	int[] Y; // Classifications of the training examples

	int k;

	public kNN_Classifier(int exampleCount, int featureCount, int classCount,
			int[][] f, int[] y, int k) {
		super();
		this.exampleCount = exampleCount;
		this.featureCount = featureCount;
		this.classCount = classCount;
		F = f;
		Y = y;
		this.k = k;
	}

	int distance(int[] features1, int[] features2) {
		int d = 0;
		// Count the Hamming distance between the two objects
		for(int i = 0; i < featureCount; i++){
			if(features1[i] != features2[i])
				d++;
		}
		return d;
	}

	int classify(int[] features) {
		ArrayList<Neighbor> N = new ArrayList<Neighbor>();
		int bestClass = -1;

		for(int i = 0; i < exampleCount; i++)
			N.add(new Neighbor(Y[i],distance(features,F[i])));


		Collections.sort(N);

		int k = 5;
		int number[] = new int[10];
		for (int i = 0; i< 10; i++)
			number[i] = 0;
		for (int i = 0; i < classCount; i++) {
			for (int j=0; j < k ; j++)
				if(N.get(j).label == i)
					number[i]++;

		}
		bestClass = 0;
		int max = number[0];
		for (int i = 0; i< 10; i++)
			if(max < number[i])
			{
				max = number[i];
				bestClass = i;
			}


		// Find out the most frequent class among the first k Neighbor objects
		// at the sorted list

		return bestClass;
	}

	class Neighbor implements Comparable<Neighbor> {
		int label;
		int distance;

		public Neighbor(int label, int distance) {
			super();
			this.label = label;
			this.distance = distance;
		}

		@Override
		public int compareTo(Neighbor n) {
			return this.distance - n.distance;
		}

	}

}
