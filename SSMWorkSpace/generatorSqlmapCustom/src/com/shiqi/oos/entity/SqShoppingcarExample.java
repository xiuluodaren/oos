package com.shiqi.oos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqShoppingcarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SqShoppingcarExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userid like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userid not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andShopnumIsNull() {
            addCriterion("shopNum is null");
            return (Criteria) this;
        }

        public Criteria andShopnumIsNotNull() {
            addCriterion("shopNum is not null");
            return (Criteria) this;
        }

        public Criteria andShopnumEqualTo(Integer value) {
            addCriterion("shopNum =", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumNotEqualTo(Integer value) {
            addCriterion("shopNum <>", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumGreaterThan(Integer value) {
            addCriterion("shopNum >", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopNum >=", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumLessThan(Integer value) {
            addCriterion("shopNum <", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumLessThanOrEqualTo(Integer value) {
            addCriterion("shopNum <=", value, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumIn(List<Integer> values) {
            addCriterion("shopNum in", values, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumNotIn(List<Integer> values) {
            addCriterion("shopNum not in", values, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumBetween(Integer value1, Integer value2) {
            addCriterion("shopNum between", value1, value2, "shopnum");
            return (Criteria) this;
        }

        public Criteria andShopnumNotBetween(Integer value1, Integer value2) {
            addCriterion("shopNum not between", value1, value2, "shopnum");
            return (Criteria) this;
        }

        public Criteria andTotalpirceIsNull() {
            addCriterion("totalPirce is null");
            return (Criteria) this;
        }

        public Criteria andTotalpirceIsNotNull() {
            addCriterion("totalPirce is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpirceEqualTo(Integer value) {
            addCriterion("totalPirce =", value, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andTotalpirceNotEqualTo(Integer value) {
            addCriterion("totalPirce <>", value, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andTotalpirceGreaterThan(Integer value) {
            addCriterion("totalPirce >", value, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andTotalpirceGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalPirce >=", value, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andTotalpirceLessThan(Integer value) {
            addCriterion("totalPirce <", value, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andTotalpirceLessThanOrEqualTo(Integer value) {
            addCriterion("totalPirce <=", value, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andTotalpirceIn(List<Integer> values) {
            addCriterion("totalPirce in", values, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andTotalpirceNotIn(List<Integer> values) {
            addCriterion("totalPirce not in", values, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andTotalpirceBetween(Integer value1, Integer value2) {
            addCriterion("totalPirce between", value1, value2, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andTotalpirceNotBetween(Integer value1, Integer value2) {
            addCriterion("totalPirce not between", value1, value2, "totalpirce");
            return (Criteria) this;
        }

        public Criteria andDiningtableidIsNull() {
            addCriterion("diningTableId is null");
            return (Criteria) this;
        }

        public Criteria andDiningtableidIsNotNull() {
            addCriterion("diningTableId is not null");
            return (Criteria) this;
        }

        public Criteria andDiningtableidEqualTo(String value) {
            addCriterion("diningTableId =", value, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidNotEqualTo(String value) {
            addCriterion("diningTableId <>", value, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidGreaterThan(String value) {
            addCriterion("diningTableId >", value, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidGreaterThanOrEqualTo(String value) {
            addCriterion("diningTableId >=", value, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidLessThan(String value) {
            addCriterion("diningTableId <", value, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidLessThanOrEqualTo(String value) {
            addCriterion("diningTableId <=", value, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidLike(String value) {
            addCriterion("diningTableId like", value, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidNotLike(String value) {
            addCriterion("diningTableId not like", value, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidIn(List<String> values) {
            addCriterion("diningTableId in", values, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidNotIn(List<String> values) {
            addCriterion("diningTableId not in", values, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidBetween(String value1, String value2) {
            addCriterion("diningTableId between", value1, value2, "diningtableid");
            return (Criteria) this;
        }

        public Criteria andDiningtableidNotBetween(String value1, String value2) {
            addCriterion("diningTableId not between", value1, value2, "diningtableid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}