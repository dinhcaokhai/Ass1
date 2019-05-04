from collections import defaultdict

class Graph:
	def __init__(self,graph):
		self.graph = graph
		self.row = len(graph)
	
	def BFS(self,s,t,parent):
		visited = [False]*(self.row)
		queue = []
		queue.append(s)
		visited[s]=True
		while queue:
			u = queue.pop(0)
			for ind, val in enumerate(self.graph[u]):
				if visited[ind] == False and val > 0:
					queue.append(ind)
					visited[ind] = True
					parent[ind] = u
					
		return True if visited[t] else False
	
	def FordAlt(self,source,sink):
		parent =[-1]*(self.row)
		max_flow = 0 
		while self.BFS(source,sink,parent):
			path_flow = float("Inf")
			s = sink
			while(s!=source):
				path_flow = min (path_flow, self.graph[parent[s]][s])
				s = parent[s]
			max_flow+=path_flow
			v = sink
			while(v!=source):
				u = parent[v]
				self.graph[u][v] -= path_flow
				self.graph[v][u] += path_flow
				v = parent[v]
		
		return max_flow
			
with open("test.txt","r") as r:
	non_blank_lines = [line for line in r.readlines() if line != '']
with open("test.txt","r") as f:
	c = f.readline()
	d = c.strip().split(" ")
	for t in range(len(d)):
		d[t] = int(d[t])
	
	matrix = []
	for i in range(6):
		a=f.readline()
		b = a.strip().split(" ")
		matrix.append(b)
	
	for j in range(len(matrix)):	
		for k in range(len(matrix[j])):
 
			try:
				matrix[j][k] = int(matrix[j][k])
				
				
			except ValueError:
				print("Invalid number format")
			
	g = Graph(matrix) 
	source = d[0]; sink = d[1]
   
print ("The maximum possible flow is %d " % g.FordAlt(source, sink)) 
  