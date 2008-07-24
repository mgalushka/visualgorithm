algorithm insertRedBlackTree(t : RedBlackTree, insertnode:Node)
return AVLNode
var y,z:Node
begin
   if (true) then 
     ;
     ;
   end
   y <- nil;
   z <- root(t,z,y);
   while (z != n(t)) do
       y <- z;
       if (key(insertnode) < key(z)) then
           z <- left(z);
       else
           z <- right(z);
       end
   end
   insertnode.father <- y;
   if (y = n(t)) then
       t.root <- insertnode;
   else
       if (key(insertnode) < key(y)) then
           y.left <- insertnode;
       else
           y.right <- insertnode;
       end
   end
   do 
    while (true) do
     ;
    end
   until(true);
   
   insertnode.left <- nl(t);
   insertnode.right <- nl(t);
   insertnode.color <- RED;
   insertCorrectionRedBlackTree(t,z);
end