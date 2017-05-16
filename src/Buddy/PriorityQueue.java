package Buddy;

import java.util.ArrayList;

public class PriorityQueue<T extends Comparable>
{
    ArrayList<T> queue = new ArrayList<>();
    int size = 0;

    public void Sort()
    {
        for(int i = size - 1; i > 0; i--)
        {
            if (queue.get(i).compareTo(queue.get(i-1)) == 1)
            {
                T temp = queue.get(i);
                queue.set(i,queue.get(i - 1));
                queue.set(i - 1, temp);
            }
            else
            {
                return;
            }
        }
    }

    public void Add(T type)
    {
        queue.add(type);
        size++;
        Sort();
    }

    public int Count()
    {
        return queue.size();
    }

    public T First()
    {
        return queue.get(0);
    }

    public T At(int index)
    {
        return queue.get(index);
    }

    public void Remove(T type)
    {
        queue.remove(type);
        size--;
    }

    public boolean Contains(T type)
    {
        return queue.contains(type);
    }

    public void Pop()
    {
        queue.remove(0);
        size--;
    }
}
