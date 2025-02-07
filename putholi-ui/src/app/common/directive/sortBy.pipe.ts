import { Pipe, PipeTransform } from '@angular/core';
import { orderBy } from 'lodash';

@Pipe({ name: 'sortBy' })
export class SortByPipe implements PipeTransform {

  transform(value: any[], order: any = '', column: Array<any> = []): any[] {
    setTimeout(() => {
      if (!value || order === '' || !order) { return value; } // no array
      if (value.length <= 1) { return value; } // array with only one item
      if (!column) {
        if (order === 'asc') {
          return value.sort()
        }
        else { return value.sort().reverse(); }
      } // sort 1d array
    })
    return orderBy(value, [column], [order]);
  }
}