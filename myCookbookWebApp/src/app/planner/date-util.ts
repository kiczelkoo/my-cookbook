import { formatDate } from '@angular/common';

export function toLongDateFormat(date: Date) {
    return formatDate(date, 'yyyy-MM-dd', 'en');
}

export function toShortDateFormat(date: Date) {
    return formatDate(date, 'dd.MM', 'en');
}

export function calculateTimeRange(date: Date, shift: number): [Date, Date] {
    const from = new Date(date);
    from.setDate(date.getDate() - shift)
    const to = new Date(date);
    to.setDate(date.getDate() + shift)
    return [from, to];
}