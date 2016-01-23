/**
 * Class to iterate in reverse order of the collection
 */

package containerMath;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author igor
 */
public class backIterator<Type> implements Iterable<Type> {
private ListIterator<Type> list;

public backIterator (List<Type> list){
    this.list = list.listIterator(list.size());
}
        public Iterator<Type> iterator() {
        return new Iterator<Type>(){
            public boolean hasNext() {
                return list.hasPrevious();
            }

            public Type next() {
                return list.previous();
            }

            public void remove() {
                list.remove();
            }

        };
      }
    }
    

