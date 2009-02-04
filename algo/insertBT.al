algorithm insertBT(tree : BinarySearchTree, node : BinarySearchNode)
return BinarySearchNode
var insertNode, y, z : BinarySearchNode
begin
  insertNode <- node;
  y <- nil;
  z <- tree.getRoot();
  
  while (z != nil) do
    y <- z;
    if (insertNode.getKey() < z.getKey()) then
      z <- z.getLeft();
    else
      z <- z.getRight();
    end
  end
  insertNode.father <- y;
  if (y = nil) then
    tree.root <- insertNode;
  else
    if (insertNode.getKey() < y.getKey()) then
      y.left <- insertNode;
    else
      y.right <- insertNode;
    end
  end
  return insertNode;
end
