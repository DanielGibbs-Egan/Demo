
public class TreeList {

	private class SubTree {
		
		private SubTree off;
		private SubTree on;
		
		private SubTree next;
		private SubTree previous;
		
		private int data;
		
		public SubTree getSub(boolean on) {
			if (on) {
				if (this.on == null) {
					this.on = new SubTree();
				}
				return this.on;
				
			};
			if (this.off == null)
				this.off = new SubTree();
			return this.off;
		}
		
		public SubTree() {}
		
	}
	
	SubTree root;
	
	int size;
	
	public TreeList() { 
		this.root = new SubTree();
		this.size = 0;
	}
	
	public void add(int index, int n) {
		
		SubTree subTree = root;
		
		while (index / 2 != 0) {
			subTree = subTree.getSub(index % 2 == 1);
			index /= 2;
		}
		
		if (index % 2 == 0) {
			subTree.data = n;
		} else {
			subTree.getSub(true).data = n;
		}
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
		
		if (index % 2 == 0) {
			return subTree.data;
		} else {
			return subTree.getSub(true).data;
		}
		
	}
	
	public void add(int n) {
		add(size, n);
	}
	
	public static void main(String[] args) {
		TreeList t = new TreeList();
		t.add(1);
		t.add(2);
		t.add(3);
		t.add(4);
		System.out.println(t.get(0));
		System.out.println(t.get(1));
		System.out.println(t.get(2));
		System.out.println(t.get(3));
	}
	
}
