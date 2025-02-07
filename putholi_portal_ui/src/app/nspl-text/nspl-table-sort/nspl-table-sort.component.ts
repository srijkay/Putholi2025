import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-nspl-table-sort',
  templateUrl: './nspl-table-sort.component.html',
  styleUrls: ['./nspl-table-sort.component.css']
})
export class NsplTableSortComponent implements OnInit {

  @Input()
  sortCol: Array<any>;

  @Input()
  sortTypeName: any;

  @Input()
  sortReverseModel: any = false;

  @Input()
  defaultSort: any;

  @Input()
  headerText: any


  private sortOrder = 1;
  private collator = new Intl.Collator(undefined, {
    numeric: true,
    sensitivity: "base",
  });

  constructor() { }

  ngOnInit(): void {
    setTimeout(() => {
      this.sortData()
    }, 500)
  }

  sortData() {
    if (this.defaultSort === "desc") {
      this.sortCol.sort(this.startSort(this.sortTypeName, this.defaultSort));
      this.defaultSort = "asc"
    }
    else {
      this.sortCol.sort(this.startSort(this.sortTypeName, this.defaultSort));
      this.defaultSort = "desc"
    }
  }

  public startSort(property, order) {
    if (order === "desc") {
      this.sortOrder = -1;
    } else {
      this.sortOrder = 1;
    }
    return (a, b) => {
      return this.collator.compare(a[property], b[property]) * this.sortOrder;
    }
  }

}
