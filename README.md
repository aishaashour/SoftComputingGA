# SoftComputingGA
Case Study: Electric Vehicle Charging

Problem to Solve:
We have a set of electric cars, each requiring a certain charging time, and a limited number of charging slots available at the station.
The goal is to assign cars to charging slots and order them efficiently to minimize total waiting time and overall completion time.

Given:
  -n electric cars, each with a charging time t_i.
  -m charging slots (m<n)
Find:
  -An assignment of cars to slots and their order on each slot that minimizes total charging time.
Components of GA:
		Encoding technique: permutation-based integer encoding.
			-Gene: Represents a car
			-Chromosome: A permutation of all car IDs indicating the charging order.
		
		Initialization Procedure: randomly generate several valid chromosomes
		Evaluation Function (Fitness Function): Fitness=1/(total charging time) (or total waiting time).
		Selection Method: Tournament Selection.
		Genetic Operators: -Crossover (Reproduction): Order Crossover
						   -Mutation: Swap Mutation 

Parameter Settings:
		Population size (N): 50
		mutation rate (m): 0.05
		crossove rate (c) : 0.9
		Number of generations (G): 100
		Tournament size (k): 3
		Slots: 2 or 3
​
