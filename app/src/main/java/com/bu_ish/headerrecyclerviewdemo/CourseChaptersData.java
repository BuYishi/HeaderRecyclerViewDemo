package com.bu_ish.headerrecyclerviewdemo;

import java.util.List;

public class CourseChaptersData {
    private List<Chapter> list;

    public List<Chapter> getList() {
        return list;
    }

    public class Chapter {
        private String name, max_name;
        private int id;
        private List<Section> video_list;
        private boolean checked;

        public String getName() {
            return name;
        }

        public String getIndexName() {
            return max_name;
        }

        public int getId() {
            return id;
        }

        public List<Section> getSections() {
            return video_list;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public boolean isChecked() {
            return checked;
        }

        public class Section {
            private String name;
            private int id;
            private int is_must_see, try_see;

            public String getName() {
                return name;
            }

            public int getId() {
                return id;
            }

            public int canTrial() {
                return try_see;
            }

            public boolean isMajorCourseRequired() {
                return is_must_see != 0;
            }
        }
    }
}
