
public class TreeList {

	private class SubTree {
		
		private SubTree left;
		private SubTree right;

		private int index;
		public SubTree next;
		
		private int data;

//		private SubTree next;
//		private SubTree previous;
//		public SubTree getLinear(boolean next) {
//			if (next)
//				return this.next;
//			return this.previous; 
//		}
		
		public SubTree getSub(boolean right) {
			if (right) {
				if (this.right == null) {
					this.right = new SubTree();
				}
				return this.right;
				
			};
			if (this.left == null)
				this.left = new SubTree();
			return this.left;
		}
		
		public SubTree() {}
		
	}
	
	SubTree root;
	SubTree lastLeaf;
	
	int size;
	
	public TreeList() { 
		this.root = this.lastLeaf = new SubTree();
		this.size = 0;
	}
	
	public void add(int index, int n) {
		
		SubTree subTree = root;
		
		while (index / 2 != 0) {
			subTree = subTree.getSub(index % 2 == 1);
			index /= 2;
		}
		
		if (index % 2 == 1) {
			subTree = subTree.getSub(true);
		}
		
		subTree.data = n;
		
		size++;
		
	}
	
	public int get(int index) {
		
		if (root == null)
			root = new SubTree();
		
		SubTree subTree = root;
		
		while (index / 2 != 0) {
			subTree = subTree.getSub(index % 2 == 1);
			index /= 2;
		}
		
		if (index % 2 == 1) {
			subTree = subTree.getSub(true);
		}
		
		return subTree.data;
		
	}
	
	public void add(int n) {
		add(size, n);
	}
	
	public static void main(String[] args) {
		TreeList t = new TreeList();
		for (int i = 0; i < 100; i++) {
			t.add(i+1);
		}
		for (int i = 0; i < t.size; i++) {
			System.out.println(t.get(i));
		}
	}
	
}
