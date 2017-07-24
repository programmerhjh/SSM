package com.ssm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PostExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PostExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andPostIdIsNull() {
            addCriterion("post_id is null");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNotNull() {
            addCriterion("post_id is not null");
            return (Criteria) this;
        }

        public Criteria andPostIdEqualTo(Integer value) {
            addCriterion("post_id =", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotEqualTo(Integer value) {
            addCriterion("post_id <>", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThan(Integer value) {
            addCriterion("post_id >", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("post_id >=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThan(Integer value) {
            addCriterion("post_id <", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThanOrEqualTo(Integer value) {
            addCriterion("post_id <=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdIn(List<Integer> values) {
            addCriterion("post_id in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotIn(List<Integer> values) {
            addCriterion("post_id not in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdBetween(Integer value1, Integer value2) {
            addCriterion("post_id between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotBetween(Integer value1, Integer value2) {
            addCriterion("post_id not between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostNameIsNull() {
            addCriterion("post_name is null");
            return (Criteria) this;
        }

        public Criteria andPostNameIsNotNull() {
            addCriterion("post_name is not null");
            return (Criteria) this;
        }

        public Criteria andPostNameEqualTo(String value) {
            addCriterion("post_name =", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotEqualTo(String value) {
            addCriterion("post_name <>", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameGreaterThan(String value) {
            addCriterion("post_name >", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameGreaterThanOrEqualTo(String value) {
            addCriterion("post_name >=", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLessThan(String value) {
            addCriterion("post_name <", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLessThanOrEqualTo(String value) {
            addCriterion("post_name <=", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLike(String value) {
            addCriterion("post_name like", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotLike(String value) {
            addCriterion("post_name not like", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameIn(List<String> values) {
            addCriterion("post_name in", values, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotIn(List<String> values) {
            addCriterion("post_name not in", values, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameBetween(String value1, String value2) {
            addCriterion("post_name between", value1, value2, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotBetween(String value1, String value2) {
            addCriterion("post_name not between", value1, value2, "postName");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeIsNull() {
            addCriterion("post_createtime is null");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeIsNotNull() {
            addCriterion("post_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("post_createtime =", value, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("post_createtime <>", value, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("post_createtime >", value, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("post_createtime >=", value, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeLessThan(Date value) {
            addCriterionForJDBCDate("post_createtime <", value, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("post_createtime <=", value, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("post_createtime in", values, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("post_createtime not in", values, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("post_createtime between", value1, value2, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostCreatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("post_createtime not between", value1, value2, "postCreatetime");
            return (Criteria) this;
        }

        public Criteria andPostAuthorIsNull() {
            addCriterion("post_author is null");
            return (Criteria) this;
        }

        public Criteria andPostAuthorIsNotNull() {
            addCriterion("post_author is not null");
            return (Criteria) this;
        }

        public Criteria andPostAuthorEqualTo(Integer value) {
            addCriterion("post_author =", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorNotEqualTo(Integer value) {
            addCriterion("post_author <>", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorGreaterThan(Integer value) {
            addCriterion("post_author >", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorGreaterThanOrEqualTo(Integer value) {
            addCriterion("post_author >=", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorLessThan(Integer value) {
            addCriterion("post_author <", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorLessThanOrEqualTo(Integer value) {
            addCriterion("post_author <=", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorIn(List<Integer> values) {
            addCriterion("post_author in", values, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorNotIn(List<Integer> values) {
            addCriterion("post_author not in", values, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorBetween(Integer value1, Integer value2) {
            addCriterion("post_author between", value1, value2, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorNotBetween(Integer value1, Integer value2) {
            addCriterion("post_author not between", value1, value2, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesIsNull() {
            addCriterion("post_clicktimes is null");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesIsNotNull() {
            addCriterion("post_clicktimes is not null");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesEqualTo(Integer value) {
            addCriterion("post_clicktimes =", value, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesNotEqualTo(Integer value) {
            addCriterion("post_clicktimes <>", value, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesGreaterThan(Integer value) {
            addCriterion("post_clicktimes >", value, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("post_clicktimes >=", value, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesLessThan(Integer value) {
            addCriterion("post_clicktimes <", value, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesLessThanOrEqualTo(Integer value) {
            addCriterion("post_clicktimes <=", value, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesIn(List<Integer> values) {
            addCriterion("post_clicktimes in", values, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesNotIn(List<Integer> values) {
            addCriterion("post_clicktimes not in", values, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesBetween(Integer value1, Integer value2) {
            addCriterion("post_clicktimes between", value1, value2, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostClicktimesNotBetween(Integer value1, Integer value2) {
            addCriterion("post_clicktimes not between", value1, value2, "postClicktimes");
            return (Criteria) this;
        }

        public Criteria andPostCategoryIsNull() {
            addCriterion("post_category is null");
            return (Criteria) this;
        }

        public Criteria andPostCategoryIsNotNull() {
            addCriterion("post_category is not null");
            return (Criteria) this;
        }

        public Criteria andPostCategoryEqualTo(String value) {
            addCriterion("post_category =", value, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryNotEqualTo(String value) {
            addCriterion("post_category <>", value, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryGreaterThan(String value) {
            addCriterion("post_category >", value, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("post_category >=", value, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryLessThan(String value) {
            addCriterion("post_category <", value, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryLessThanOrEqualTo(String value) {
            addCriterion("post_category <=", value, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryLike(String value) {
            addCriterion("post_category like", value, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryNotLike(String value) {
            addCriterion("post_category not like", value, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryIn(List<String> values) {
            addCriterion("post_category in", values, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryNotIn(List<String> values) {
            addCriterion("post_category not in", values, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryBetween(String value1, String value2) {
            addCriterion("post_category between", value1, value2, "postCategory");
            return (Criteria) this;
        }

        public Criteria andPostCategoryNotBetween(String value1, String value2) {
            addCriterion("post_category not between", value1, value2, "postCategory");
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